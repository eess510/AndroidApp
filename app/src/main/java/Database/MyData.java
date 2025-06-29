package Database;

public class MyData {
    public String name, tel,address,artistname,position;

    public void setName(String name) {this.name = name;}
    public void setTel(String tel) {this.tel = tel;}
    public void setAddress(String address) {this.address = address;}
    public void setArtistname(String artistname) {this.artistname = artistname;}
    public void setPosition(String position) {this.position = position;}



    public String getName(){return this.name;}
    public String getAddress(){return this.address;}
    public String getTel(){return this.tel;}
    public String getArtistname(){return this.artistname;}
    public String getPosition() {return  this.position;}

}
