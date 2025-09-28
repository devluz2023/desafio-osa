 package desafioOsa.desafioosa.model;

 import jakarta.persistence.Entity;
 import jakarta.persistence.Table;
 import jakarta.persistence.Id;
 import jakarta.persistence.GeneratedValue;
 import jakarta.persistence.GenerationType;
 import jakarta.persistence.Column;
 import lombok.Builder;
 import lombok.Data;
 import lombok.AllArgsConstructor;
 import lombok.NoArgsConstructor;
 import lombok.EqualsAndHashCode;

 @Entity
 @Table(name = "users")
 @Builder
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @EqualsAndHashCode(onlyExplicitlyIncluded = true)
 public class User {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(nullable = false)
     private String fullName;

     @Column(nullable = false, unique = true)
     private String cpf;

     @Column(nullable = false, unique = true)
     private String login;

     @Column(nullable = false)
     private String password;
 }