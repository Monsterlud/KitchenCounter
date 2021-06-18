package com.monsalud.kitchencounter.Database;

import androidx.room.TypeConverter;

import com.monsalud.kitchencounter.Entity.Beverage;
import com.monsalud.kitchencounter.Entity.Dairy;
import com.monsalud.kitchencounter.Entity.DryGood;
import com.monsalud.kitchencounter.Entity.Employee;
import com.monsalud.kitchencounter.Entity.JanitorialGood;
import com.monsalud.kitchencounter.Entity.Meat;
import com.monsalud.kitchencounter.Entity.PaperGood;
import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.Entity.Seafood;
import com.monsalud.kitchencounter.Entity.Vendor;
import com.monsalud.kitchencounter.Tables.InventoryItem;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Converters {

    //Converters for EpochMilli, LocalDateTime & LocalDate
    @TypeConverter
    public static LocalDateTime fromEpochMilliToLocalDateTime(Long epoch) {
        Instant instant = Instant.ofEpochMilli(epoch);
        LocalDateTime dateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return dateTime;
    }

    @TypeConverter
    public static Long localDateTimeToEpochMilli(LocalDateTime dateTime) {
        ZonedDateTime zdt = dateTime.atZone(ZoneId.systemDefault());
        long instant = zdt.toInstant().toEpochMilli();
        return instant;
    }

    @TypeConverter
    public static LocalDate fromEpochMilliToLocalDate(Long epoch) {
        if (epoch != null) {
            Instant instant = Instant.ofEpochMilli(epoch);
            LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            return date;
        } else return null;
    }
    @TypeConverter
    public static Long localDateToEpochMilli(LocalDate date) {
        ZonedDateTime zdt = date.atStartOfDay(ZoneId.systemDefault());
        long instant = zdt.toInstant().toEpochMilli();
        return instant;
    }

    //Converters for ProductType
    @TypeConverter
    public int fromProductType(Product.ProductType productType) {
        return productType.ordinal();
    }

    @TypeConverter
    public Product.ProductType toProductType(int ordinal) {
        if(ordinal == 0) return Product.ProductType.VEGETABLE;
        else if(ordinal == 1) return Product.ProductType.FRUIT;
        else if(ordinal == 2) return Product.ProductType.SEAFOOD;
        else if(ordinal == 3 ) return Product.ProductType.DAIRY;
        else if(ordinal == 4) return Product.ProductType.MEAT;
        else if(ordinal == 5) return Product.ProductType.DRYGOOD;
        else if(ordinal == 6) return Product.ProductType.BEVERAGE;
        else if(ordinal == 7) return Product.ProductType.PAPERGOOD;
        else if(ordinal == 8) return Product.ProductType.JANITORIALGOOD;
        else return null;
    }

    //Converters for InventoryUnit
    @TypeConverter
    public int fromInventoryUnit(InventoryItem.InventoryUnit inventoryUnit) {
        if (inventoryUnit != null) {
            return inventoryUnit.ordinal();
        } else return 0;
    }

    @TypeConverter
    public InventoryItem.InventoryUnit toInventoryUnit(int ordinal) {
            if (ordinal == 0) return InventoryItem.InventoryUnit.EACH;
            else if (ordinal == 1) return InventoryItem.InventoryUnit.BUNCH;
            else if (ordinal == 2) return InventoryItem.InventoryUnit.CASE;
            else if (ordinal == 3) return InventoryItem.InventoryUnit.KG;
            else if (ordinal == 4) return InventoryItem.InventoryUnit.LB;
            else if (ordinal == 5) return InventoryItem.InventoryUnit.OZ;
            else if (ordinal == 6) return InventoryItem.InventoryUnit.DOZEN;
            else return null;
    }


    //Converters for InventoryLocation
    @TypeConverter
    public int fromInventoryLocation(InventoryItem.InventoryLocation inventoryLocation) {
        return inventoryLocation.ordinal();
    }

    @TypeConverter
    public InventoryItem.InventoryLocation toInventoryLocation(int ordinal) {
        if (ordinal == 0) return InventoryItem.InventoryLocation.CENTRALWALKIN_SHELF_A;
        else if (ordinal == 1) return InventoryItem.InventoryLocation.CENTRALWALKIN_SHELF_B;
        else if (ordinal == 2) return InventoryItem.InventoryLocation.CENTRALWALKIN_SHELF_C;
        else if (ordinal == 3) return InventoryItem.InventoryLocation.CENTRALWALKIN_SHELF_D;
        else if (ordinal == 4) return InventoryItem.InventoryLocation.CENTRALWALKIN_SHELF_E;
        else if (ordinal == 5) return InventoryItem.InventoryLocation.CENTRALWALKIN_SHELF_F;
        else if (ordinal == 6) return InventoryItem.InventoryLocation.CENTRALDRYGOODS;
        else if (ordinal == 7) return InventoryItem.InventoryLocation.CENTRALHOTLINE;
        else if (ordinal == 8) return InventoryItem.InventoryLocation.CENTRALCASHIER;
        else if (ordinal == 9) return InventoryItem.InventoryLocation.PLAZAWALKIN;
        else if (ordinal == 10) return InventoryItem.InventoryLocation.PLAZADRYGOODS;
        else if (ordinal == 11) return InventoryItem.InventoryLocation.PLAZAFREEZER;
        else if (ordinal == 12) return InventoryItem.InventoryLocation.BROWNBAGMAIN;
        else if (ordinal == 13) return InventoryItem.InventoryLocation.BROWNBAGSATELLITE;
        else if (ordinal == 14) return InventoryItem.InventoryLocation.UNDERTHESTAIRS;
        else if (ordinal == 15) return InventoryItem.InventoryLocation.LOADINGDOCK;
        else return InventoryItem.InventoryLocation.OTHER;
    }

    //Converters for BeverageType
    @TypeConverter
    public int fromBeverageType(Beverage.BeverageType beverageType) {
        return beverageType.ordinal();
    }

    @TypeConverter
    public Beverage.BeverageType toBeverageType(int ordinal) {
        if (ordinal == 0) return Beverage.BeverageType.COFFEE;
        else if (ordinal == 1) return Beverage.BeverageType.TEA;
        else if (ordinal == 2) return Beverage.BeverageType.SOFTDRINKS;
        else if (ordinal == 3) return Beverage.BeverageType.WATER;
        else if (ordinal == 4) return Beverage.BeverageType.JUICE;
        else return Beverage.BeverageType.OTHER;
    }

    //Converters for DairyType
    @TypeConverter
    public int fromDairyType(Dairy.DairyType dairyType) {
        return dairyType.ordinal();
    }

    @TypeConverter
    public Dairy.DairyType toDairyType(int ordinal) {
        if (ordinal == 0) return Dairy.DairyType.MILKANDCREAM;
        else if (ordinal == 1) return Dairy.DairyType.CHEESE;
        else if (ordinal == 2) return Dairy.DairyType.CULTURED;
        else if (ordinal == 3) return Dairy.DairyType.EGGS;
        else return Dairy.DairyType.OTHER;
    }

    //Converters for DryGoodsCategory
    @TypeConverter
    public int fromDryGoodsCategory(DryGood.DryGoodsCategory dryGoodsCategory) {
        return dryGoodsCategory.ordinal();
    }

    @TypeConverter
    public DryGood.DryGoodsCategory toDryGoodsCategory(int ordinal) {
        if (ordinal == 0) return DryGood.DryGoodsCategory.GRAINS;
        else if (ordinal == 1) return DryGood.DryGoodsCategory.BEANS;
        else if (ordinal == 2) return DryGood.DryGoodsCategory.PASTA;
        else if (ordinal == 3) return DryGood.DryGoodsCategory.ASIAN;
        else if (ordinal == 4) return DryGood.DryGoodsCategory.NUTS;
        else if (ordinal == 5) return DryGood.DryGoodsCategory.BAKING;
        else if (ordinal == 6) return DryGood.DryGoodsCategory.SPICES;
        else if (ordinal == 7) return DryGood.DryGoodsCategory.CANNED;
        else return DryGood.DryGoodsCategory.OTHER;
    }

    //Converters for EmployeeRole
    @TypeConverter
    public int fromEmployeeRole(Employee.EmployeeRole employeeRole) {
        return employeeRole.ordinal();
    }

    @TypeConverter
    public Employee.EmployeeRole toEmployeeRole(int ordinal) {
        if (ordinal == 0) return Employee.EmployeeRole.CULINARY;
        else if (ordinal == 1) return Employee.EmployeeRole.CASHIER;
        else if (ordinal == 2) return Employee.EmployeeRole.CHEF;
        else return null;
    }

    //Converters for JanitorialGoodsCategory
    @TypeConverter
    public int fromJanitorialGoodsCategory(JanitorialGood.JanitorialGoodsCategory janitorialGoodsCategory) {
        return janitorialGoodsCategory.ordinal();
    }

    @TypeConverter
    public JanitorialGood.JanitorialGoodsCategory toJanitorialGoodsCategory(int ordinal) {
        if (ordinal == 0) return JanitorialGood.JanitorialGoodsCategory.CHEMICALS;
        else if (ordinal == 1) return JanitorialGood.JanitorialGoodsCategory.CLEANINGSUPPLIES;
        else return JanitorialGood.JanitorialGoodsCategory.OTHER;
    }

    //Converters for MeatType
    @TypeConverter
    public int fromMeatType(Meat.MeatType meatType) {
        return meatType.ordinal();
    }

    @TypeConverter
    public Meat.MeatType toMeatType(int ordinal) {
        if (ordinal == 0) return Meat.MeatType.BEEF;
        else if (ordinal == 1) return Meat.MeatType.PORK;
        else if (ordinal == 2) return Meat.MeatType.LAMB;
        else if (ordinal == 3) return Meat.MeatType.GOAT;
        else if (ordinal == 4) return Meat.MeatType.POULTRY;
        else if (ordinal == 5) return Meat.MeatType.WILDGAME;
        else return Meat.MeatType.OTHER;
    }

    //Converters for PaperGoodsCategory
    @TypeConverter
    public int fromPaperGoodsCategory(PaperGood.PaperGoodsCategory paperGoodsCategory) {
        return paperGoodsCategory.ordinal();
    }

    @TypeConverter
    public PaperGood.PaperGoodsCategory toPaperGoodsCategory(int ordinal) {
        if (ordinal == 0) return PaperGood.PaperGoodsCategory.TOGOPACKAGING;
        else if (ordinal == 1) return PaperGood.PaperGoodsCategory.DISPOSABLES;
        else return PaperGood.PaperGoodsCategory.OTHER;
    }

    //Converters for SeafoodType
    @TypeConverter
    public int fromSeafoodType(Seafood.SeafoodType seafoodType) {
        return seafoodType.ordinal();
    }

    @TypeConverter
    public Seafood.SeafoodType toSeafoodType(int ordinal) {
        if (ordinal == 0) return Seafood.SeafoodType.FISH;
        else if (ordinal == 1) return Seafood.SeafoodType.SHELLFISH;
        else if (ordinal == 2) return Seafood.SeafoodType.BIVALVE;
        else return Seafood.SeafoodType.OTHER;
    }

    //Converters for State
    @TypeConverter
    public int fromState(Vendor.State state) {
        return state.ordinal();
    }

    @TypeConverter
    public Vendor.State toState(int ordinal) {
        if (ordinal == 0) return Vendor.State.AL;
        else if (ordinal == 1) return Vendor.State.AK;
        else if (ordinal == 2) return Vendor.State.AZ;
        else if (ordinal == 3) return Vendor.State.AR;
        else if (ordinal == 4) return Vendor.State.CA;
        else if (ordinal == 5) return Vendor.State.CO;
        else if (ordinal == 6) return Vendor.State.CT;
        else if (ordinal == 7) return Vendor.State.DE;
        else if (ordinal == 8) return Vendor.State.DC;
        else if (ordinal == 9) return Vendor.State.FL;
        else if (ordinal == 10) return Vendor.State.GA;
        else if (ordinal == 11) return Vendor.State.HI;
        else if (ordinal == 12) return Vendor.State.ID;
        else if (ordinal == 13) return Vendor.State.IL;
        else if (ordinal == 14) return Vendor.State.IN;
        else if (ordinal == 15) return Vendor.State.IA;
        else if (ordinal == 16) return Vendor.State.KS;
        else if (ordinal == 17) return Vendor.State.KY;
        else if (ordinal == 18) return Vendor.State.LA;
        else if (ordinal == 19) return Vendor.State.ME;
        else if (ordinal == 20) return Vendor.State.MD;
        else if (ordinal == 21) return Vendor.State.MA;
        else if (ordinal == 22) return Vendor.State.MI;
        else if (ordinal == 23) return Vendor.State.MN;
        else if (ordinal == 24) return Vendor.State.MS;
        else if (ordinal == 25) return Vendor.State.MO;
        else if (ordinal == 26) return Vendor.State.MT;
        else if (ordinal == 27) return Vendor.State.NE;
        else if (ordinal == 28) return Vendor.State.NV;
        else if (ordinal == 29) return Vendor.State.NH;
        else if (ordinal == 30) return Vendor.State.NJ;
        else if (ordinal == 31) return Vendor.State.NM;
        else if (ordinal == 32) return Vendor.State.NY;
        else if (ordinal == 33) return Vendor.State.NC;
        else if (ordinal == 34) return Vendor.State.ND;
        else if (ordinal == 35) return Vendor.State.OH;
        else if (ordinal == 36) return Vendor.State.OK;
        else if (ordinal == 37) return Vendor.State.OR;
        else if (ordinal == 38) return Vendor.State.PA;
        else if (ordinal == 39) return Vendor.State.RI;
        else if (ordinal == 40) return Vendor.State.SC;
        else if (ordinal == 41) return Vendor.State.SD;
        else if (ordinal == 42) return Vendor.State.TN;
        else if (ordinal == 43) return Vendor.State.TX;
        else if (ordinal == 44) return Vendor.State.UT;
        else if (ordinal == 45) return Vendor.State.VT;
        else if (ordinal == 46) return Vendor.State.VA;
        else if (ordinal == 47) return Vendor.State.WA;
        else if (ordinal == 48) return Vendor.State.WV;
        else if (ordinal == 49) return Vendor.State.WI;
        else if (ordinal == 50) return Vendor.State.WY;
        else return null;
    }
}