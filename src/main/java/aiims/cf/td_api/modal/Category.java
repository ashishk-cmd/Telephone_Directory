package aiims.cf.td_api.modal;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="building")
public class Category {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String name;
		private String status;
//		@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//    	private Set<SubCategory> subCategories;

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
//		public Set<SubCategory> getSubCategories() {
//			return subCategories;
//		}
//		public void setSubCategories(Set<SubCategory> subCategories) {
//			this.subCategories = subCategories;
//		}
		public Category() {
			super();
			// TODO Auto-generated constructor stub
		}
//		public Category(Long id, String name, String status, Set<SubCategory> subCategories) {
//			super();
//			this.id = id;
//			this.name = name;
//			this.status = status;
//			this.subCategories = subCategories;
//		}
		
		
		
		
}
