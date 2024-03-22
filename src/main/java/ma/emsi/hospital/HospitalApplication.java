package ma.emsi.hospital;

import ma.emsi.hospital.entities.Medecin;
import ma.emsi.hospital.entities.Patient;
import ma.emsi.hospital.repositories.MedecinRepository;
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
	CommandLineRunner start(PatientRepository patientRepository,
							MedecinRepository medecinRepository){
		return args->{
			Stream.of("Mohammed","Hassan","Najat")
					.forEach(name->{
						Patient patient=new Patient();
						patient.setDateNaissnace(new Date());
						patient.setNom(name);
						patient.setMalade(false);
						patientRepository.save(patient);
					});
			Stream.of("Medecin1","Medecin2","Medecin3")
					.forEach(name->{
						Medecin medecin =new Medecin ();
						medecin.setNom(name);
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiset");

						medecinRepository.save(medecin);
					});

		};
	}
}
