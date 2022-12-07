package kr.ac.mjc.rental_app;

public class Record {
    private String rental_start;
    private String rental_finish;
    private String rental_image;

    public String getRental_start() {
        return rental_start;
    }

    public void setRental_start(String rental_start) {
        this.rental_start = rental_start;
    }

    public String getRental_finish() {
        return rental_finish;
    }

    public void setRental_finish(String rental_finish) {
        this.rental_finish = rental_finish;
    }

    public String getRental_image() {
        return rental_image;
    }

    public void setRental_image(String rental_image) {
        this.rental_image = rental_image;
    }
}
