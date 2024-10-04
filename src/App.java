import Body.Comments;
import Body.ManagerPosts;
import Body.Post;
import Body.User;
import Screens.LoginScreen;
import Structs.List_User;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

    public static void main(String... args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        
        User user = new User();
        user.setAge(21);
        user.setCity("Taubaté-SP");
        user.setCivil("Solteiro");
        user.setEmail("samuel@gmail.com");
        user.setPassword("1234");
        user.setName("Samuel");
        user.setPhotoProfile("/home/samuel/Desktop/Project_Java/Project_social_media/src/Screens/samuelphoto.jpeg");
        List_User.getPoint(5).add(user);

        {
            user = new User();
            user.setAge(21);
            user.setCity("Cruzeiro-SP");
            user.setCivil("Solteiro");
            user.setEmail("jose@gmail.com");
            user.setPassword("1234");
            user.setName("José");
            user.setPhotoProfile("/home/samuel/Desktop/Project_Java/Project_social_media/src/Screens/airplane.jpg");
            List_User.getPoint(5).add(user);
            List_User.getPoint(0).user[0].AddFriends(1);
            List_User.getPoint(0).user[1].AddFriends(0);
        }

        {
            user = new User();
            user.setAge(21);
            user.setCity("Lorena-SP");
            user.setCivil("Solteiro");
            user.setEmail("benedita@gmail.com");
            user.setPassword("1234");
            user.setName("Benedita");
            //user.setPhotoProfile("./airplane.jpg");
            List_User.getPoint(5).add(user);
            List_User.getPoint(0).user[0].AddFriends(2);
            List_User.getPoint(0).user[2].AddFriends(0);
        }

        {
            user = new User();
            user.setAge(21);
            user.setCity("Pinda-SP");
            user.setCivil("Solteiro");
            user.setEmail("kleber@gmail.com");
            user.setPassword("1234");
            user.setName("Kleber");
            user.setPhotoProfile("/home/samuel/Desktop/Project_Java/Project_social_media/src/Screens/samuelphoto.jpeg");
            List_User.getPoint(5).add(user);
            List_User.getPoint(0).user[0].getSolicit().add(3);
            List_User.getPoint(0).user[0].getSolicit().add(1);
        }

        {
            user = new User();
            user.setAge(21);
            user.setCity("Taubaté-SP");
            user.setCivil("Solteiro");
            user.setEmail("zeca@gmail.com");
            user.setPassword("1234");
            user.setName("Zeca");
            //user.setPhotoProfile("./airplane.jpg");
            List_User.getPoint(5).add(user);
        }

        Post post = new Post();
        post.setId((short)0);
        post.setIduser((short)0);
        
        post.setImagem("/home/samuel/Desktop/Project_Java/Project_social_media/src/Screens/airplane.jpg");
        post.setTitle("Titulo para Testes");
        post.setPostTxt("Texto para testes do Posts Texto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do PostsTexto para testes do Posts");

        {
        Comments comment = new Comments();
        comment.setId((short)0);
        comment.setIdUser((short)0);
        comment.setComment("Comentários");
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);
        post.getComments().add(comment);

        }
        
        List_User.getPoint(0).user[0].getPosts().add(post);
        ManagerPosts.geralPosts.add(post);
        
        //new Examples();
        new LoginScreen().getStage().show();
    }
}
