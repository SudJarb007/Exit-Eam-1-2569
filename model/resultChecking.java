package model;
// คลาสนี้จะทำผลตรวจสอบ
public class resultChecking
{
    private boolean success;
    private String message;

    public resultChecking(boolean success, String message)
    {
        this.success = success;
        this.message = message;
    }
    public boolean isSuccess()
    {
        return success;
    }
    public String getMessage()
    {
        return message;
    }
}