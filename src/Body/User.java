package Body;

import java.util.ArrayList;

public class User {

    private String name, password, city, civil, email , photoProfile=null;
    private int age;
    private ArrayList<Post> postUser = new ArrayList<>();
    private ArrayList<Integer> friends = new ArrayList<>();
    private ArrayList<Integer> solicit = new ArrayList<>();

    public void AddFriends(int idFriend){
        this.friends.add(idFriend);
    }
    public void removeFriends(int idFriend){
        for(int i =0;i<this.friends.size();++i){
            if(this.friends.get(i)==idFriend){
                this.friends.remove(i);
                return;
            }
        }
    }
    
    public ArrayList<Integer> getFriends(){
        return this.friends;
    }
    public byte checkFriend(int id){
        for(int i =0;i<this.friends.size();++i){
            if(this.friends.get(i)==id) return 0;
        }
        return 1;
    }

    public void sendSolict(int idFriend){
        solicit.add(idFriend);
    }
    public void removeSolicit(int idFriend){
        for(int i =0;i<this.solicit.size();++i){
            if(idFriend==this.solicit.get(i)){
                this.solicit.remove(i);
                return;
            }
        }
    }
    public ArrayList<Integer> getSolicit(){
        return this.solicit;
    }

    public String getPhotoProfile() {
        return photoProfile;
    }
    public void setPhotoProfile(String photoProfile) {
        this.photoProfile = photoProfile;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCivil() {
        return civil;
    }
    public void setCivil(String civil) {
        this.civil = civil;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Post> getPosts(){return this.postUser;}

}
