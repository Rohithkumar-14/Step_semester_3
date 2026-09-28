import java.util.*;

interface ScoringRule {
    double calculateScore(double idea, double execution, double presentation);
}

class InnovationScoring implements ScoringRule {
    public double calculateScore(double idea, double execution, double presentation) {
        return idea * 0.50 + execution * 0.30 + presentation * 0.20;
    }
}

class OpenScoring implements ScoringRule {
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Track {
    private String name;
    private ScoringRule scoringRule;

    public Track(String name, ScoringRule scoringRule) {
        this.name = name;
        this.scoringRule = scoringRule;
    }

    public String getName() {
        return name;
    }

    public double calculateScore(double idea, double execution, double presentation) {
        return scoringRule.calculateScore(idea, execution, presentation);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Judge {
    private String name;

    public Judge(String name) {
        this.name = name;
    }
}

class Project {
    private String name;
    private Team team;
    private Double finalScore;

    public Project(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setFinalScore(double score) {
        finalScore = score;
    }

    public double getFinalScore() {
        return finalScore;
    }
}

class Score {
    private double idea;
    private double execution;
    private double presentation;

    public Score(double idea, double execution, double presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
    }

    public double calculate(Track track) {
        return track.calculateScore(idea, execution, presentation);
    }

    public void setIdea(double idea) {
        this.idea = idea;
    }
}

class Team {
    private String name;
    private List<Student> members;
    private Track track;
    private Project project;

    public Team(String name, List<Student> members, Track track) {
        this.name = name;
        this.members = members;
        this.track = track;
    }

    public boolean containsStudent(Student student) {
        return members.contains(student);
    }

    public boolean hasProject() {
        return project != null;
    }

    public void submitProject(String projectName) {
        if (project == null) {
            project = new Project(projectName, this);
            System.out.println("Project '" + projectName + "' submitted by " + name + ".");
        }
    }

    public Project getProject() {
        return project;
    }

    public Track getTrack() {
        return track;
    }

    public String getName() {
        return name;
    }

    public int getMemberCount() {
        return members.size();
    }
}

class Hackathon {
    private String name;
    private List<Team> teams = new ArrayList<>();
    private Set<Student> registeredStudents = new HashSet<>();
    private String state = "Open";
    private Map<Project, Score> scores = new HashMap<>();

    public Hackathon(String name) {
        this.name = name;
    }

    public void registerTeam(Team team) {
        if (!state.equals("Open")) {
            System.out.println("Registration failed: Hackathon is not open.");
            return;
        }

        if (team.getMemberCount() < 2 || team.getMemberCount() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return;
        }

        for (Student student : getMembers(team)) {
            if (registeredStudents.contains(student)) {
                System.out.println("Registration failed: Student already belongs to another team.");
                return;
            }
        }

        teams.add(team);
        registeredStudents.addAll(getMembers(team));

        System.out.println("Team " + team.getName() + " registered (" +
                team.getMemberCount() + " members, " + team.getTrack().getName() + " track).");
    }

    private List<Student> getMembers(Team team) {
        List<Student> result = new ArrayList<>();
        for (Student s : allStudents(team))
            result.add(s);
        return result;
    }

    private List<Student> allStudents(Team team) {
        try {
            java.lang.reflect.Field field = Team.class.getDeclaredField("members");
            field.setAccessible(true);
            return (List<Student>) field.get(team);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void startJudging() {
        state = "Judging";
    }

    public void submitScore(Project project, Score score) {
        if (state.equals("Published")) {
            System.out.println("Score rejected: Results have already been published.");
            return;
        }

        scores.put(project, score);
        System.out.println("Score recorded for '" + project.getName() + "'.");
    }

    public void publishResults() {
        state = "Published";

        for (Map.Entry<Project, Score> entry : scores.entrySet()) {
            Project project = entry.getKey();
            Score score = entry.getValue();
            double finalScore = score.calculate(project.getTeam().getTrack());
            project.setFinalScore(finalScore);

            System.out.printf("Final score: %.2f%n", finalScore);
        }

        System.out.println("Results published.");
    }

    public void rescore(Project project, double idea, double execution, double presentation) {
        if (state.equals("Published")) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }

        Score oldScore = scores.get(project);

        if (oldScore != null) {
            oldScore.setIdea(idea);
        }
    }
}

public class P1_CodeSprintJudgingDesk {
    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon("Code Sprint");

        Track innovation = new Track("Innovation", new InnovationScoring());
        Track open = new Track("Open", new OpenScoring());

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        Student kiran = new Student("Kiran");

        Team byteBusters = new Team(
                "ByteBusters",
                Arrays.asList(asha, ravi, neha),
                innovation
        );

        Team soloCoder = new Team(
                "SoloCoder",
                Arrays.asList(kiran),
                open
        );

        hackathon.registerTeam(byteBusters);
        hackathon.registerTeam(soloCoder);

        byteBusters.submitProject("SmartAttend");

        hackathon.startJudging();

        Score score = new Score(8, 7, 9);
        hackathon.submitScore(byteBusters.getProject(), score);

        hackathon.publishResults();

        hackathon.rescore(byteBusters.getProject(), 10, 7, 9);
    }
}