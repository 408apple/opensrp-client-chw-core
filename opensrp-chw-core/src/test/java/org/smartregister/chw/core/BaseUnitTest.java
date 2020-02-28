package org.smartregister.chw.core;


import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.smartregister.chw.core.application.TestApplication;
import org.smartregister.chw.core.shadows.ContextShadow;
import org.smartregister.chw.core.shadows.ShadowFamilyLibrary;

/**
 * @author rkodev
 */
@RunWith(RobolectricTestRunner.class)
@Config(application = TestApplication.class, sdk = 22, shadows = {ContextShadow.class, ShadowFamilyLibrary.class})
public abstract class BaseUnitTest {

}
