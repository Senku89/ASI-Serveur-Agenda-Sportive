package fr.upjv.agendasportive.models;

public class InscriptionRequest {
    private int userId;
    private int coursId;

    public InscriptionRequest(int userId, int coursId) {
        this.userId = userId;
        this.coursId = coursId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCoursId() {
        return coursId;
    }

    public void setCoursId(int coursId) {
        this.coursId = coursId;
    }
}