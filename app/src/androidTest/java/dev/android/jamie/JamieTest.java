package dev.android.jamie;


import android.content.Context;
import android.graphics.Canvas;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class JamieTest {

    private static final String FAKE_STRING = "HELLO WORLD";

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("dev.android.jamie", appContext.getPackageName());
        try {
            runOnUiThread(new Runnable() {
                public void run() {
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.cg.initialize();
                    assertTrue(! mainActivity.cg.gameOver);
                    assertTrue(! mainActivity.cg.paused);
                    mainActivity.cg.onDraw(new Canvas());
                    assertTrue(! mainActivity.cg.gameOver);
                    mainActivity.cg.restart(new Canvas());
                }
            });
        } catch (Throwable t) {}
    }
}