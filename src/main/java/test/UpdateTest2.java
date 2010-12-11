package test;

import java.io.File;

import net.praqma.clearcase.ucm.entities.UCM;
import net.praqma.clearcase.ucm.view.SnapshotView;
import net.praqma.clearcase.ucm.view.UCMView;
import net.praqma.clearcase.ucm.view.SnapshotView.COMP;
import net.praqma.utils.Command;

public class UpdateTest2
{
	public static void main( String[] args )
	{
		UCM.SetContext( UCM.ContextType.CLEARTOOL );
		
		//File root = new File( "C:\\Temp\\views\\chw_Server_11_dev_view\\" );
		File root = new File( "C:\\Temp\\views2\\test1" );

		
		if( args.length > 0 )
		{
			root = new File( args[0] );
			System.out.println( "Setting view root to " + root );
		}

		System.out.println( "Starting..." );
		
		//SnapshotView view = UCMView.GetSnapshotView( root );
		//String cmd = "cleartool update -force  -overwrite  -add_loadrules  Cool\\Model Cool\\Trace Cool\\ServerTest Cool\\Gui";
		String[] cmd = { "cleartool", "update", "-force", "-overwrite", "-add_loadrules", "Cool\\Model", "Cool\\Trace", "Cool\\ServerTest", "Cool\\Gui" };
		
		Command.run( cmd, root );
		
	}
}
