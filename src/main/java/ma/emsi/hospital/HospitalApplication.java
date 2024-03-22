package ma.emsi.hospital;

import ma.emsi.hospital.entities.*;
import ma.emsi.hospital.repositories.ConsultationRepository;
import ma.emsi.hospital.repositories.MedecinRepository;
import ma.emsi.hospital.repositories.PatientRepository;
import ma.emsi.hospital.repositories.RendezVousRepository;
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
							MedecinRepository medecinRepository,
							RendezVousRepository rendezVousRepository,
							ConsultationRepository consultationRepository){
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
						medecin.setEmail(name+"@gmail.com");
						medecinRepository.save(medecin);
					});
            Patient patient=patientRepository.findById(1L).orElse(null);
			Patient patient1=patientRepository.findByNom("Hassan");
			Medecin medecin=medecinRepository.findByNom("Medecin1");


			RendezVous rendezVous=new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			rendezVousRepository.save(rendezVous);

			RendezVous rendezVous1=rendezVousRepository.findById(1L).orElse(null);
			Consultation consultation=new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous);
			consultation.setRapport("Rapport de la cnsultation ....");
			consultationRepository.save(consultation);


		};
	}
}
