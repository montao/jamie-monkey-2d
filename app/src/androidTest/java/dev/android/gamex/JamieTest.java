package dev.android.gamex;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.test.InstrumentationRegistry;
import android.widget.TextView;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JamieTest {

    private static final String FAKE_STRING = "HELLO WORLD";



    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("dev.android.gamex", appContext.getPackageName());
    }
}