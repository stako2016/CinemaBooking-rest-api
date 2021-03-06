package aerialarts.model;
// default package
// Generated Apr 10, 2016 8:48:13 AM by Hibernate Tools 3.4.0.CR1


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CinemaUser generated by hbm2java
 */
@Entity
@Table(name = "cinema_user", catalog = "cinema")
public class CinemaUser implements java.io.Serializable {

	private Integer id;
	private Role role;
	private String name;
	private String password;

	public CinemaUser() {
	}

	public CinemaUser(Role role, String name, String password) {
		this.role = role;
		this.name = name;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false) 
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "name", nullable = false, length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", nullable = false, length = 25)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		
		return super.toString();
	}

}