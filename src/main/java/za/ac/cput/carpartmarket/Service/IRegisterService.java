package za.ac.cput.carpartmarket.Service;

import za.ac.cput.carpartmarket.Domain.Register;

import java.util.List;

public interface IRegisterService extends IService<Register, String>{

    List<Register> getall();
}
