package folk.sisby.switchy;

import folk.sisby.switchy.api.SwitchyEvents;
import net.minecraft.resource.ResourceType;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;

public class SwitchyCardinal implements SwitchyEvents.Init {
	@Override
	public void onInitialize() {
		if (QuiltLoader.isModLoaded("cardinal-components-base") && QuiltLoader.isModLoaded("cardinal-components-entity")) {
			ResourceLoader.get(ResourceType.SERVER_DATA).registerReloader(CardinalModuleLoader.INSTANCE);
		}
	}
}
