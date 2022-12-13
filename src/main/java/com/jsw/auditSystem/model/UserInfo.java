package com.jsw.auditSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user_info")
@Immutable
public class UserInfo {
  @Id
  @GeneratedValue(generator = "avail_user_id_seq", strategy = GenerationType.AUTO)
  private Long id;

  private String email;


  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String firstname;

  @Column(nullable = false)
  private String lastname;

 /* @Enumerated(EnumType.STRING)
  private UserStatus status;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(
          name = "user_group_id",
          referencedColumnName = "id",
          nullable = true,
          foreignKey = @ForeignKey(name = "avail_user_user_group_fk"))
  private UserGroup userGroup;*/

  private boolean accountSetupFinished;


}
