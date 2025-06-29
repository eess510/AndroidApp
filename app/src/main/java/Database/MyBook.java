package Database;


public class MyBook {

    public String name,tel , address;

    public MyBook() {
        this.name = name;
        this.tel = tel;
        this.address = address;

    }

    public void setName(String name) {this.name = name;}
    public void setTel(String tel) {this.tel = tel;}

    public void setAddress(String address) {this.address = address;}


    public String getName(){return name;}
    public String getTel() {return  this.tel;}
    public String getAddress(){return address;}

}


