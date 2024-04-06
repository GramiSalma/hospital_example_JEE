package ma.emsi.hospital;

import ma.emsi.hospital.entities.*;
import ma.emsi.hospital.repositories.ConsultationRepository;
import ma.emsi.hospital.repositories.MedecinRepository;
import ma.emsi.hospital.repositories.PatientRepository;
import ma.emsi.hospital.repositories.RendezVousRepository;
import ma.emsi.hospital.service.IHospitalService;
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
	CommandLineRunner start(IHospitalService hospitalService,PatientRepository patientRepository,RendezVousRepository rendezVousRepository,MedecinRepository medecinRepository){
		return args->{
			Stream.of("Mohammed","Hassan","Najat")
					.forEach(name->{
						Patient patient=new Patient();
						patient.setDateNaissnace(new Date());
						patient.setNom(name);
						patient.setMalade(false);
						hospitalService.savePatient(patient);
					});
			Stream.of("Medecin1","Medecin2","Medecin3")
					.forEach(name->{
						Medecin medecin =new Medecin ();
						medecin.setNom(name);
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiset");
						medecin.setEmail(name+"@gmail.com");
						hospitalService.saveMedecin(medecin);
					});
            Patient patient=patientRepository.findById(1L).orElse(null);
			Patient patient1=patientRepository.findByNom("Hassan");
			Medecin medecin=medecinRepository.findByNom("Medecin1");


			RendezVous rendezVous=new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			RendezVous SaveDRD=hospitalService.saveRendezVous(rendezVous);
			System.out.println(SaveDRD.getId());




			RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
			Consultation consultation=new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous);
			consultation.setRapport("Rapport de la consultation ....");
			hospitalService.saveConsultation(consultation);


		};
	}
}
