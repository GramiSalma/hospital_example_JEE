package ma.emsi.hospital;

import ma.emsi.hospital.entities.Patient;
import ma.emsi.hospital.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication  {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
@Bean
	CommandLineRunner start(PatientRepository patientRepository){
		return args->{
			Stream.of("Mohammed","Hassan","Najat")
					.forEach(name->{
						Patient patient=new Patient();
						patient.setDateNaissnace(new Date());
						patient.setNom(name);
						patient.setMalade(false);
						patientRepository.save(patient);
					});

		};
	}
}
