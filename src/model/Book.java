package model;

public class Book {

    private int id;
    private String name;
    private String category;
    private int publish_year;
    private int total_copies;
    private int available_copies;

    // setters for instance
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setCategory(String category) {this.category = category;}
    public void setPublish_year(int publish_year) {this.publish_year = publish_year;}
    public void setTotal_copies(int total_copies) {this.total_copies = total_copies;}
    public void setAvailable_copies(int available_copies) {this.available_copies = available_copies;}

    // getters for instance
    public int getId() {return id;}
    public String getName() {return name;}
    public String getCategory() {return category;}
    public int getPublish_year() {return publish_year;}
    public int getTotal_copies() {return total_copies;}
    public int getAvailable_copies() {return available_copies;}

    public String toString(){
        return String.format("%-10d %-45s %-15s %-15d %-15d% -15d",id,name, category, publish_year, total_copies, available_copies);

    }
}
