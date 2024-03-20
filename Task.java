import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Task {
    private String taskId;
    private String data;
    private int difficulty;
    private boolean solved;


    public Task(String data, int difficulty) {
        this.taskId = UUID.randomUUID().toString();
        this.data = data;
        this.difficulty = difficulty;
        this.solved = false;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }


    //METHODES A IMPLEMENTER

    public String calculateHash() {
        try {
            // Création de l'instance du message digest avec l'algorithme SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Hashage des données
            byte[] hashBytes = digest.digest(data.getBytes());

            // Conversion des bytes du hash en une représentation hexadécimale
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Ajout des zéros au début du hash en fonction de la difficulté
            StringBuilder prefixZeros = new StringBuilder();
            for (int i = 0; i < difficulty; i++) {
                prefixZeros.append("0");
            }
            hexString.insert(0, prefixZeros.toString());

            return hexString.toString(); // Retourne le hash en format hexadécimal avec des zéros au début
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Gestion de l'exception en cas d'algorithme de hachage non disponible
            return null; // Ou lancez une exception appropriée selon votre logique d'erreur
        }
    }


    // Méthode pour valider si le hash respecte la difficulté de la tâche
    public boolean validateHash(String hash) {
        // Construire le préfixe requis en fonction de la difficulté
        StringBuilder prefixBuilder = new StringBuilder();
        for (int i = 0; i < difficulty; i++) {
            prefixBuilder.append("0");
        }
        String requiredPrefix = prefixBuilder.toString();

        // Vérifier si le hash commence par le préfixe requis
        if (!hash.startsWith(requiredPrefix)) {
            return false; // Si le préfixe ne correspond pas, le hash ne respecte pas la difficulté
        }

        // Vérifier si le reste du hash contient d'autres zéros indésirables
        for (int i = difficulty; i < hash.length(); i++) {
            if (hash.charAt(i) != '0') {
                return false; // S'il y a d'autres caractères que '0' après le préfixe, le hash est invalide
            }
        }

        return true; // Si le hash commence par le préfixe et ne contient que des '0' supplémentaires, il est valide
    }



}
