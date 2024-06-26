package ma.emsi.hospital.service;

import ma.emsi.hospital.entities.Consultation;
import ma.emsi.hospital.entities.Medecin;
import ma.emsi.hospital.entities.RendezVous;
import ma.emsi.hospital.entities.Patient;

public interface IHospitalService {
   Patient savePatient(Patient patient);
   Medecin  saveMedecin(Medecin medecin);
   RendezVous saveRendezVous(RendezVous rendezVous);
   Consultation saveConsultation(Consultation consultation);

}
