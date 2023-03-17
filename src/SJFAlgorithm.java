import java.util.Scanner;

public class SJFAlgorithm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = input.nextInt();

        int[] burstTime = new int[numProcesses];
        int[] arrivalTime = new int[numProcesses];

        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter the burst time for process " + (i + 1) + ": ");
            burstTime[i] = input.nextInt();

            System.out.print("Enter the arrival time for process " + (i + 1) + ": ");
            arrivalTime[i] = input.nextInt();
        }

        // Sort the processes based on their burst time using selection sort
        for (int i = 0; i < numProcesses - 1; i++) {
            int shortestBurstTimeIndex = i;

            for (int j = i + 1; j < numProcesses; j++) {
                if (burstTime[j] < burstTime[shortestBurstTimeIndex]) {
                    shortestBurstTimeIndex = j;
                }
            }

            int temp = burstTime[i];
            burstTime[i] = burstTime[shortestBurstTimeIndex];
            burstTime[shortestBurstTimeIndex] = temp;

            temp = arrivalTime[i];
            arrivalTime[i] = arrivalTime[shortestBurstTimeIndex];
            arrivalTime[shortestBurstTimeIndex] = temp;
        }

        // Calculate waiting and turnaround time for each process
        int[] waitingTime = new int[numProcesses];
        int[] turnaroundTime = new int[numProcesses];

        int currentTime = arrivalTime[0];
        double totalwaitingtime = 0;
        double totalturnaroundtime  = 0;
        for (int i = 0; i < numProcesses; i++) {
            waitingTime[i] = currentTime - arrivalTime[i];
            turnaroundTime[i] = waitingTime[i] + burstTime[i];
            currentTime += burstTime[i];
            totalwaitingtime = +waitingTime[i];
            totalturnaroundtime = +turnaroundTime[i];
        }
        double avgwaitingtime = totalwaitingtime / numProcesses;
        double avgturnaroundtime = totalturnaroundtime / numProcesses;

        // Print the results
        System.out.println("Process\tBurst Time\tArrival Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println((i + 1) + "\t\t\t" + burstTime[i] + "\t\t\t" + arrivalTime[i] + "\t\t\t\t" + waitingTime[i] + "\t\t\t\t" + turnaroundTime[i]);
        }
        System.out.println(" Average waiting time = " + avgwaitingtime);
        System.out.println(" Average turn around time = " + avgturnaroundtime);
    }
}
