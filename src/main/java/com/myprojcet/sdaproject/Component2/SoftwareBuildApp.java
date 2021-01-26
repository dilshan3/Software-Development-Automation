package com.myprojcet.sdaproject.Component2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SoftwareBuildApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
//        String []arr = {"Waiter", "inherits", "Employee", "RoomBoy", "inherits", 
//            "Employee", "Chef", "inherits", "Employee", "Waiter", "aggregates", 
//            "Restaurant", "Chef", "aggregates", "Restaurant", "FoodItem", 
//            "composes", "Restaurant", "RoomBoy", "composes", "RoomService",
//            "Manager", "inherits", "Employee", "Customer", "associates",
//            "Manager", "Manager", "associates", "Stock", "Manager", "associates",
//            "TaxiService", "Taxi", "composes", "TaxiService", "Customer",
//            "associates", "Taxi", "Taxi", "associates", "Bill", "Room",
//            "directional associates", "Bill", "Customer", "associates",
//            "Restaurant", "Customer", "associates", "Room", "Customer", "composes", "Bill"};
//        RelationshipMapper mapper = new RelationshipMapper();
//        List<Relationship> reList = mapper.mapToXl(arr);
//        Relationship rel;
          
//           
//        for(int i = 0; i < reList.size(); i++){
//              
//            rel = reList.get(i);
//            System.out.println(rel.getClassa()+" "+rel.getRelationship()+" "+rel.getClassb());
//              
//        }
//       
//        String excelFilePath = "ClassRelationships.xls";
//
//        mapper.writeToXls(reList, excelFilePath);

          new Index().setVisible(true);            
        
    }
    
}

