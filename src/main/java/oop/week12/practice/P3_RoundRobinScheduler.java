
package oop.week12.practice;

import java.util.LinkedList;
import java.util.Queue;

public class P3_RoundRobinScheduler {

    static class Job {
        String name;
        int remainingTime;

        Job(String name, int remainingTime) {
            this.name = name;
            this.remainingTime = remainingTime;
        }
    }

    public static void main(String[] args) {

        int quantum = 3;

        Queue<Job> queue = new LinkedList<>();

        queue.add(new Job("P1", 5));
        queue.add(new Job("P2", 3));
        queue.add(new Job("P3", 7));

        int time = 0;

        StringBuilder slices = new StringBuilder();
        StringBuilder finish = new StringBuilder();

        while (!queue.isEmpty()) {

            Job current = queue.remove();

            int start = time;

            int runTime = Math.min(quantum, current.remainingTime);

            time += runTime;
            current.remainingTime -= runTime;

            slices.append(current.name)
                  .append("(")
                  .append(start)
                  .append("-")
                  .append(time)
                  .append(") ");

            if (current.remainingTime > 0) {
                queue.add(current);
            }
            else {
                if (finish.length() > 0) {
                    finish.append(", ");
                }

                finish.append(current.name)
                      .append(" ")
                      .append(time);
            }
        }

        System.out.println(slices.toString().trim());
        System.out.println("finish " + finish);
    }
}
