package com.jsw.auditSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Immutable
@Table(name = "user_info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {

  @Id
  @Column
  @Logger(value = "id", showData = true)
  private Long id;

  @Column
  @Logger(value = "email", showData = true)
  private String email;

  @Column(nullable = false)
  @Logger(value = "password", showData = true)
  private String password;

  @Column(nullable = false)
  @Logger(value ="firstname", showData = true)
  private String firstname;

  @Column(nullable = false)
  @Logger(value = "lastname", showData = true)
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

  @Column
  @Logger(value = "accountSetupFinished", showData = true)
  private boolean accountSetupFinished;
}
