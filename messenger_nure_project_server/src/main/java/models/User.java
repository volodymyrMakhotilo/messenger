package models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Column(name = "nickname")
        private String nickname;
     //   private int age;
       /* @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Auto> autos;
        */

        public User() {
        }

        public User(String name) {
            this.nickname = name;
        }


        public int getId() {
            return id;
        }

        public String getName() {
            return nickname;
        }

        public void setName(String name) {
            this.nickname = name;
        }




        @Override
        public String toString() {
            return "models.User{" +
                    "name='" + nickname + '\'' +
                    '}';
        }


}
