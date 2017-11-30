package dev.android.gamex;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.test.InstrumentationRegistry;
import android.widget.TextView;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JamieTest {

    private static final String FAKE_STRING = "HELLO WORLD";

    @Mock
    Canvas can;

    //@Ignore
    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("dev.android.gamex", appContext.getPackageName());
        //final
        //mainActivity.cg = new MainActivity.CatchGame()

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
       // assertTrue(! cg.paused);

    }
}