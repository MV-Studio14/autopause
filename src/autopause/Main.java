package autopause;

import arc.Events;
import arc.util.CommandHandler;
import arc.util.Log;
import mindustry.core.GameState;
import mindustry.game.EventType;
import mindustry.gen.Groups;
import mindustry.mod.Plugin;

import static mindustry.Vars.state;

public class Main extends Plugin {

    private boolean enabled = true;
    private int minPlayers = 1;

    @Override
    public void init(){
        // on player join: resume
        Events.on(EventType.PlayerJoin.class, event -> {
            if (state.isPaused() && Groups.player.size() >= this.minPlayers && this.enabled){
                this.pause(false);
            }
        });

        // on player leave: pause
        Events.on(EventType.PlayerLeave.class, event -> {
            if (Groups.player.size() -1 < this.minPlayers && this.enabled){
                this.pause(true);
            }
        });

        Log.info("[autopause] Autopause plugin loaded!\nMinimum player count: " + this.minPlayers);
    }

    // pause if 'on' == true
    public void pause(boolean on){
        if (on){
            state.serverPaused = true;
            Log.info("[autopause] Game auto paused");
        } else {
            state.serverPaused = false;
            Log.info("[autopause] Game auto resumed");
        }
    }

    @Override
    public void registerServerCommands(CommandHandler handler){
        handler.register("autopause", "Toggle enable to the autopause plugin", args -> {
            this.enabled = !this.enabled;
            Log.info("[autopause] now autopause is " + (this.enabled? "ENABLED" : "DISABLED"));

            if(this.enabled){
                this.pause(true);
            }
        });

        handler.register("setminplayer", "<min-players>", "Set minimum player count in autopause plugin", args -> {
            try{
                this.minPlayers = Integer.parseInt(args[0]);
            } catch (RuntimeException e){
                Log.info("[autopause] Invalid argument. 'min-players' must be an integer >= 1");
            }

            if (this.minPlayers < 1){
                this.minPlayers = 1;
                Log.info("[autopause] 'min-players' must be greater than or equal to 1");
            }

            Log.info("[autopause] Minimum player count set to " + this.minPlayers);
        });
    }
}
