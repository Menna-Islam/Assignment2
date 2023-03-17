import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RoundRobinAlgorithm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int numProcesses = input.nextInt();

        ArrayList<Integer> processList = new ArrayList<Integer>();
        ArrayList<Integer> arrivalTimeList = new ArrayList<Integer>();
        ArrayList<Integer> burstTimeList = new ArrayList<Integer>();

        System.out.println("Enter the arrival time and burst time for each process:");
        for (int i = 0; i < numProcesses; i++) {

            System.out.print("Process " + (i + 1) + " burst time: ");
            int burstTime = input.nextInt();
            burstTimeList.add(burstTime);

            System.out.print("Process " + (i + 1) + " arrival time: ");
            int arrivalTime = input.nextInt();
            arrivalTimeList.add(arrivalTime);



            processList.add(i + 1);
        }

        System.out.print("Enter the quantum time: ");
        int quantumTime = input.nextInt();

        // Round Robin Scheduling Algorithm
        Queue<Integer> readyQueue = new LinkedList<Integer>();
        int[] remainingTime = new int[numProcesses];
        int[] waitingTime = new int[numProcesses];
        int[] turnaroundTime = new int[numProcesses];
        double totalwaitingtime = 0;
        double totalturnaroundtime  = 0;
        for (int i = 0; i < numProcesses; i++) {
            remainingTime[i] = burstTimeList.get(i);
            totalwaitingtime = +waitingTime[i];
            totalturnaroundtime = +turnaroundTime[i];
        }

        int currentTime = 0;
        readyQueue.add(0);

        while (!readyQueue.isEmpty()) {
            int currentProcess = readyQueue.remove();
            if (remainingTime[currentProcess] <= quantumTime) {
                currentTime += remainingTime[currentProcess];
                for (int i = 0; i < numProcesses; i++) {
                    if (arrivalTimeList.get(i) <= currentTime && remainingTime[i] > 0) {
                        readyQueue.add(i);
                    }
                }
                waitingTime[currentProcess] = currentTime - burstTimeList.get(currentProcess) - arrivalTimeList.get(currentProcess);
                turnaroundTime[currentProcess] = currentTime - arrivalTimeList.get(currentProcess);
                remainingTime[currentProcess] = 0;
            } else {
                currentTime += quantumTime;
                remainingTime[currentProcess] -= quantumTime;
                for (int i = 0; i < numProcesses; i++) {
                    if (arrivalTimeList.get(i) <= currentTime && remainingTime[i] > 0 && !readyQueue.contains(i)) {
                        readyQueue.add(i);
                    }
                }
                readyQueue.add(currentProcess);
            }
        }

        // Calculate average waiting and turnaround time
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        for (int i = 0; i < numProcesses; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }
        double avgwaitingtime = totalWaitingTime / numProcesses;
        double avgturnaroundtime = totalTurnaroundTime / numProcesses;

        // Print the results
        System.out.println("\nProcess\tBurst Time\tArrival Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println(processList.get(i) + "\t\t\t" + burstTimeList.get(i) + "\t\t\t" + arrivalTimeList.get(i) + "\t\t\t" + waitingTime[i] + "\t\t\t\t" + turnaroundTime[i]);
        }
        System.out.println(" Average waiting time = " + avgwaitingtime);
        System.out.println(" Average turn around time = " + avgturnaroundtime);

    }
}