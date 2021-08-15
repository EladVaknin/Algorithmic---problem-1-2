package GlassBall;

public class GlassBall {
    /**
     * Returns the minimum floor that causes breaking ball - using 2 balls and dividing the building into equal parts
     * Complexity: O(sqrt(n)) - 2*sqrt(n)
     */
    public static int glassBall1(int[] floors, int ball) {
        int n = floors.length;
        int step = (int) Math.sqrt(n);
        int currentFloor = step;
        boolean isBreak = false;
        while(!isBreak) {
            if(floors[currentFloor] > ball) {
                currentFloor = currentFloor - step + 1;
                while(!isBreak) {
                    if(floors[currentFloor] > ball) {
                        return currentFloor;
                    }
                    currentFloor++;
                }
            }
            if(currentFloor == n-1) break;
            currentFloor += step;
            if(currentFloor > n-1) currentFloor = n-1;
        }
        return n;
    }

    /**
     * Returns the minimum floor that causes breaking ball - using 2 balls and dividing the building into different parts
     * Complexity: O(sqrt(n)) - sqrt(2*n)
     */
    public static int glassBall2(int[] floors, int ball) {
        int n = floors.length;
        int step = 1;
        while((step*(step+1))/2 < n) {
            step++;
        }
        int currentFloor = step;
        boolean isBreak = false;
        while(!isBreak) {
            if(floors[currentFloor] > ball) {
                currentFloor = currentFloor - step + 1;
                while(!isBreak) {
                    if(floors[currentFloor] > ball) {
                        return currentFloor;
                    }
                    currentFloor++;
                }
            }
            if(currentFloor == n-1) break;
            step--;
            currentFloor += step;
            if(currentFloor > n-1) currentFloor = n-1;
        }
        return n;
    }
    public static void main(String[] args) {
        System.out.println(glassBall2(new int[] {10,20,30,40,50,60,70},55));
    }
}