package br.com.sermil.manager.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service")
@Data
@NoArgsConstructor
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Condominium condominium;
	@ManyToOne
	private User technician;
	private LocalDateTime realizationDate = LocalDateTime.now();
	private String perfomedService;
	@Enumerated(EnumType.STRING) // GRAVA NO BD O NOME DA CONSTANTE DO ENUM NO LUGAR DA ONDEM DE DECLARAÇÃO.
	private StatusService status;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public Service(Condominium condominium, User technician, LocalDateTime realizationDate, String perfomedService,
			StatusService status) {
		this.condominium = condominium;
		this.technician = technician;
		this.realizationDate = realizationDate;
		this.perfomedService = perfomedService;
		this.status = status;
	}

}
