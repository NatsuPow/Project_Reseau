
public class MainServeur {

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        //Test création d'une tâche
        Task task = new Task("Faire une présentation", 5);
        System.out.println("Task ID: " + task.getTaskId());
        System.out.println("Data: " + task.getData());
        System.out.println("Difficulty: " + task.getDifficulty());
        System.out.println("Solved: " + task.isSolved());

        System.out.println(task.calculateHash());
        System.out.println(task.validateHash(task.calculateHash()));

        System.out.println("c'est bon");


    }

}
