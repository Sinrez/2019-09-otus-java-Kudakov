package ru.otus.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.homework.atm.ATM;
import ru.otus.homework.atm.MyATM;
import ru.otus.homework.bills.Bill;
import ru.otus.homework.cassettes.Cassette;
import ru.otus.homework.cassettes.EmptyCassetteException;
import ru.otus.homework.dispensers.DispenserImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.homework.bills.Bill.*;

class DepartmentATMTest {

    private static final int amountRub10 = 12;
    private static final int amountRub50 = 64;
    private static final int amountRub100 = 32;
    private static final int amountRub200 = 23;
    private static final int amountRub500 = 13;
    private static final int amountRub1000 = 734;
    private static final int amountRub2000 = 25;
    private static final int amountRub5000 = 42;
    private static final int amountATM = 10;
    private List<ATM> atms = new ArrayList<>();
    private int expectedDepartmentAtmBalance;
    private int atmBalance;
    private DepartmentATM departmentATM;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < amountATM; i++) {
            Map<Bill, Cassette> cassettes = new HashMap<>();
            cassettes.put(RUB10, new Cassette());
            cassettes.put(RUB50, new Cassette());
            cassettes.put(RUB100, new Cassette());
            cassettes.put(RUB200, new Cassette());
            cassettes.put(RUB500, new Cassette());
            cassettes.put(RUB1000, new Cassette());
            cassettes.put(RUB2000, new Cassette());
            cassettes.put(RUB5000, new Cassette());
            ATM atm = new MyATM(new DispenserImpl(cassettes));
            Map<Bill, Integer> expectedBillsCashIn;
            expectedBillsCashIn = new HashMap<>();
            expectedBillsCashIn.put(RUB10, amountRub10);
            expectedBillsCashIn.put(RUB50, amountRub50);
            expectedBillsCashIn.put(RUB100, amountRub100);
            expectedBillsCashIn.put(RUB200, amountRub200);
            expectedBillsCashIn.put(RUB500, amountRub500);
            expectedBillsCashIn.put(RUB1000, amountRub1000);
            expectedBillsCashIn.put(RUB2000, amountRub2000);
            expectedBillsCashIn.put(RUB5000, amountRub5000);
            atm.cashIn(expectedBillsCashIn);
            atms.add(atm);
        }
        departmentATM = new DepartmentATM(atms);
        atmBalance = amountRub10 * RUB10.getNominal() +
                amountRub50 * RUB50.getNominal() +
                amountRub100 * RUB100.getNominal() +
                amountRub200 * RUB200.getNominal() +
                amountRub500 * RUB500.getNominal() +
                amountRub1000 * RUB1000.getNominal() +
                amountRub2000 * RUB2000.getNominal() +
                amountRub5000 * RUB5000.getNominal();
        expectedDepartmentAtmBalance = amountATM * atmBalance;
    }

    @Test
    void getBalance() throws EmptyCassetteException, CashOutException {
        assertEquals(expectedDepartmentAtmBalance, departmentATM.getBalance());
        for (ATM atm : atms) {
            atm.cashOut(1000);
        }
        expectedDepartmentAtmBalance = expectedDepartmentAtmBalance - 1000 * amountATM;
        assertEquals(expectedDepartmentAtmBalance, departmentATM.getBalance());
    }

    @Test
    void backUp() throws EmptyCassetteException, CashOutException {
        for (int i = 0; i < 10; i++) {
            for (ATM atm : atms) {
                atm.cashOut(atmBalance);
            }
            departmentATM.backUp();

            assertEquals(expectedDepartmentAtmBalance, departmentATM.getBalance());
        }
    }
}