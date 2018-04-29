package com.example.phundal2091.basicapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by phundal2091 on 4/28/18.
 */

@RunWith(AndroidJUnit4.class)
public class QuestionBankTest {

    @Test
    public void testFileRead() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        QuestionBank questionBank = new QuestionBank(appContext);
        String s = questionBank.readJSONFromAsset(appContext);

        assertNotNull(s);
    }
}
