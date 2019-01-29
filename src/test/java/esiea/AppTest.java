package esiea;

import org.junit.jupiter.api.Test;
//import org.assertj.core.api.Assertions.*;
import  org.assertj.core.api.Assertions.assertThat;  // main one
import  org.assertj.core.api.Assertions.atIndex; // for List assertions
import  org.assertj.core.api.Assertions.entry;  // for Map assertions
import  org.assertj.core.api.Assertions.tuple; // when extracting several properties at once
import  org.assertj.core.api.Assertions.fail; // use when writing exception tests
import  org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown; // idem
import  org.assertj.core.api.Assertions.filter; // for Iterable/Array assertions
import  org.assertj.core.api.Assertions.offset; // for floating number assertions
import  org.assertj.core.api.Assertions.anyOf; // use with Condition
import  org.assertj.core.api.Assertions.contentOf; // use with File assertions

public class AppTest 

{
	@Test
    public void testApp()
    {
	Assertions.assertThat(true).isTrue();
    }

}

