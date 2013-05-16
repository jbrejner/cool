package net.praqma.clearcase.test.unit;

import java.io.File;
import java.util.logging.Level;

import net.praqma.clearcase.Cool;
import net.praqma.clearcase.exceptions.ClearCaseException;
import net.praqma.clearcase.exceptions.UnableToInitializeEntityException;
import net.praqma.clearcase.ucm.entities.*;
import net.praqma.junit.LoggingRule;
import net.praqma.util.execute.CommandLineInterface.OperatingSystem;

import org.junit.ClassRule;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class VersionTest {
	
	@ClassRule
    public static LoggingRule lrule = new LoggingRule( Level.ALL, "net.praqma" );

	@Test
	public void getVersion() throws ClearCaseException {
		String version = "c:\\code\\lib\\common.h@@\\main\\int\\1";
		
		Version v = Version.getVersion( version );
		
		assertThat( v.getRevision(), is( 1 ) );
		assertThat( v.getVersion(), is( "\\main\\int\\1" ) );
		assertThat( v.getBranch(), is( "\\main\\int" ) );
		if( Cool.getOS().equals( OperatingSystem.WINDOWS ) ) {
			assertThat( v.getFile(), is( new File( "c:\\code\\lib\\common.h" ) ) );
		}
		
		String version2 = "/code/lib/common.h@@/main/int/1";
		
		Version v2 = Version.getVersion( version2 );
		
		assertThat( v2.getRevision(), is( 1 ) );
		assertThat( v2.getVersion(), is( "/main/int/1" ) );
		assertThat( v2.getBranch(), is( "/main/int" ) );
		if( Cool.getOS().equals( OperatingSystem.UNIX ) ) {
			assertThat( v2.getFile(), is( new File( "/code/lib/common.h" ) ) );
		}
	}
	
	@Test
	public void getVersionNew() throws ClearCaseException {
		String version = "c:\\code\\lib\\common.h@@\\main\\int\\1";
		
		Version v = Version.getVersion( version );
		
		assertThat( v.getRevision(), is( 1 ) );
		assertThat( v.getVersion(), is( "\\main\\int\\1" ) );
		assertThat( v.getBranch(), is( "\\main\\int" ) );
		if( Cool.getOS().equals( OperatingSystem.WINDOWS ) ) {
			assertThat( v.getFile(), is( new File( "c:\\code\\lib\\common.h" ) ) );
		}
		
		String version2 = "/code/lib/common.h@@/main/int/1";
		
		Version v2 = Version.getVersion( version2 );
		
		assertThat( v2.getRevision(), is( 1 ) );
		assertThat( v2.getVersion(), is( "/main/int/1" ) );
		assertThat( v2.getBranch(), is( "/main/int" ) );
		if( Cool.getOS().equals( OperatingSystem.UNIX ) ) {
			assertThat( v2.getFile(), is( new File( "/code/lib/common.h" ) ) );
		}
	}

    @Test
    public void testBranches() throws UnableToInitializeEntityException {
        String s = "C:\\views\\chw-server\\night-vobadmin_one_int_3\\crot\\Model@@\\main\\wolles_dev\\1\\wolles.txt";
        Version v = Version.get( s );

        assertThat( v.getBranches().size(), is( 2 ) );
        assertThat( v.getBranches().get( 0 ).getName(), is( "main" ) );
        assertThat( v.getBranches().get( 1 ).getName(), is( "wolles_dev" ) );
    }

    @Test
    public void testOffBranchedVersions() throws UnableToInitializeEntityException {
        String s = "C:\\views\\chw-server\\night-vobadmin_one_int_3\\crot\\Model@@\\main\\wolles_dev\\1\\wolles.txt";
        Version v = Version.get( s );

        assertThat( v.getFile().getAbsolutePath(), is( "C:\\views\\chw-server\\night-vobadmin_one_int_3\\crot\\Model\\wolles.txt" ) );
        assertTrue( v.isOffBranched() );
    }

    @Test
    public void testOffBranchedVersionsEmpty() throws UnableToInitializeEntityException {
        String s = "C:\\views\\chw-server\\night-vobadmin_one_int_3\\crot\\Model@@\\main\\wolles_dev\\1";
        Version v = Version.get( s );

        assertThat( v.getFile().getAbsolutePath(), is( "C:\\views\\chw-server\\night-vobadmin_one_int_3\\crot\\Model" ) );
        assertFalse( v.isOffBranched() );
    }
}