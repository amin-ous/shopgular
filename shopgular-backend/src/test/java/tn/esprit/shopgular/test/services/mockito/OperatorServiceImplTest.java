// package tn.esprit.shopgular.test.services.mockito;
// import static org.junit.Assert.assertEquals;
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.when;

// import org.mockito.ArgumentMatchers;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.MockitoJUnitRunner;

// import tn.esprit.shopgular.repositories.OperatorRepository;
// import tn.esprit.shopgular.services.OperatorServiceImpl;


// import java.io.*;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;
// import java.util.stream.Stream;

// import org.junit.Before;
// import org.junit.jupiter.api.*;
// import org.junit.jupiter.api.MethodOrderer.*;
// import org.junit.runner.*;
// import org.springframework.beans.factory.annotation.*;
// import org.springframework.boot.test.context.*;
// import org.springframework.security.crypto.password.*;
// import org.springframework.test.context.junit4.*;
// import tn.esprit.shopgular.entities.*;
// import tn.esprit.shopgular.models.*;
// import tn.esprit.shopgular.services.*;

// @RunWith(MockitoJUnitRunner.class)
// public class OperatorServiceImplTest {
	
//     @Mock
//     OperatorRepository operatorRepository;
//     @InjectMocks
//     OperatorServiceImpl operatorService;
   
   
//     Operator operator = new Operator( 1l,"Test Add", "Operator TAO", "Test Add Operator", null);


//     // @Test
//     // public void testretrieveOperateur(){
//     //     List<Operator> operators = new ArrayList<>();
//     //     when(operatorRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(operator)); //find all
//     //     Operator op1 = operateurService.getOperator(2L);
//     //     Assertions.assertNotNull(op1);
//     //        System.out.println("woooorkiiiiing retrieve !");
   
   
//     // }


//     @Test
// 	public void retrieveAllOperateursTest() throws IOException {
// 		when(operatorRepository.findAll()).thenReturn(Stream.of(
//                 new Operator((long)1,"Rain","Do",null,"Niar"),
//                 new Operator((long)2,"Rain","Sometimes",null,"Niar"), 
// 				new Operator((long)3,"Rain","Codes",null,"Niar"))
//                 .collect(Collectors.toList()));
// 		assertEquals(3,operatorService.getAllOperators().size());
		
// 	}

//     /**
//      * 
//      */
//     @Test
//     public void testAddOperator() throws IOException {
//         Operator operator = new Operator( 1l,"Test Add", "Operator TAO",null, "Test Add Operator");
//         Mockito.when(operatorRepository.save(operator)).thenReturn(operator);
//         Operator op = operatorService.addOperator(operator);
//         assertEquals(op, operatorService.addOperator(op));
//            System.out.println("woooorkiiiiing add !");
   
   
//     }
   
//     // @Test
//     // public void testretrieveAllOperateurs() {
//     //     Mockito.when(operateurRepositoryMock.findAll()).thenReturn(listOperateurs);
//     //     List<Operateur> listOp = operateurService.retrieveAllOperateurs();
//     //     Assertions.assertNotNull(listOp);
//     //        System.out.println("woooorkiiiiing all retrieve !");
   
//     // }
   
   
   
//     // -
   
//    }
