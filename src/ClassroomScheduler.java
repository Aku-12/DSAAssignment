import java.util.*;

public class ClassroomScheduler {

    static class Class {
        int start;
        int end;
        int students;

        public Class(int start, int end, int students) {
            this.start = start;
            this.end = end;
            this.students = students;
        }
    }

    public static int getBusiestClassroom(int n, int[][] classes) {
        //Sorting classes based on start time, then by number of students in descending order
        Arrays.sort(classes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[2] - a[2];
            }
            return a[0] - b[0];
        });

        // Priority Queue to keep track of available rooms and their next free time
        PriorityQueue<int[]> roomPQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Mapping to count the number of classes held in each room
        int[] roomCount = new int[n];

        // Initializing the rooms in the priority queue
        for (int i = 0; i < n; i++) {
            roomPQ.offer(new int[]{0, i});
        }

        // Processing each class
        for (int[] cls : classes) {
            int startTime = cls[0];
            int endTime = cls[1];
            int students = cls[2];

            // Getting the next available room
            int[] room = roomPQ.poll();
            int roomEndTime = room[0];
            int roomIndex = room[1];

            // If the room is not free before the start time of the class, delaying the class
            if (roomEndTime > startTime) {
                startTime = roomEndTime;
            }

            // Assigning the room and update the end time
            roomEndTime = startTime + (endTime - cls[0]);
            roomPQ.offer(new int[]{roomEndTime, roomIndex});

            // Incrementing the count of classes held in this room
            roomCount[roomIndex]++;
        }

        // Finding the room with the maximum number of classes
        int maxClasses = 0;
        int busiestRoom = 0;
        for (int i = 0; i < n; i++) {
            if (roomCount[i] > maxClasses || (roomCount[i] == maxClasses && i < busiestRoom)) {
                maxClasses = roomCount[i];
                busiestRoom = i;
            }
        }

        return busiestRoom;
    }

    public static void main(String[] args) {
        int n1 = 2;
        int[][] classes1 = {{0, 10, 30}, {1, 5, 20}, {2, 7, 25}, {3, 4, 10}};
        System.out.println(getBusiestClassroom(n1, classes1)); // Output: 0

        int n2 = 3;
        int[][] classes2 = {{1, 20, 50}, {2, 10, 40}, {3, 5, 30}, {4, 9, 25}, {6, 8, 20}};
        System.out.println(getBusiestClassroom(n2, classes2)); // Output: 1
    }
}
