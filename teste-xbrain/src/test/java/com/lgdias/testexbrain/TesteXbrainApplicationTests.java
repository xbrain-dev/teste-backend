package com.lgdias.testexbrain;

import com.lgdias.testexbrain.service.VendaServiceImplTest;
import com.lgdias.testexbrain.service.VendedorServiceImplTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    VendaServiceImplTest.class,
    VendedorServiceImplTest.class
})
public class TesteXbrainApplicationTests {

  @Test
  public void contextLoads() {
  }

}
