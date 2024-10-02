package anon.seamlessauth;

import net.minecraft.network.EnumConnectionState;

import anon.seamlessauth.auth.network.packet.ChallengeRequest;
import anon.seamlessauth.auth.network.packet.ChallengeResponse;
import anon.seamlessauth.auth.network.packet.KeyRequest;
import anon.seamlessauth.auth.network.packet.KeyResponse;
import anon.seamlessauth.skin.network.PacketDispatcher;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        SeamlessAuth.LOG.info(Tags.MODNAME + " (" + Tags.VERSION + ") loading...");
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {
        /** packet registration **/
        /* server-side */
        EnumConnectionState.LOGIN.func_150751_a(2, KeyResponse.class);
        EnumConnectionState.LOGIN.func_150751_a(3, ChallengeResponse.class);
        /* client-side */
        EnumConnectionState.LOGIN.func_150756_b(3, KeyRequest.class);
        EnumConnectionState.LOGIN.func_150756_b(4, ChallengeRequest.class);

        PacketDispatcher.registerPackets();
    }

    public void serverStarting(FMLServerStartingEvent event) {}
}
