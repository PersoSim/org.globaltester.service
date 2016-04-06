package org.globaltester.service;

import java.util.HashSet;

/**
 * Abstract super implementation of a {@link GtService}.
 * <p/>
 * Basically handles registration and notification of {@link GtServiceListener}s
 * @author amay
 *
 */
public abstract class AbstractGtService implements GtService {
	
	protected HashSet<GtServiceListener> listeners = new HashSet<>();

	@Override
	public void registerServiceListener(GtServiceListener listener) {
		listeners.add(listener);

	}

	@Override
	public void unregisterServiceListener(GtServiceListener listener) {
		listeners.remove(listener);
	}
	
	protected void notifyStatusChange(boolean newStatus) {
		for (GtServiceListener curListener : listeners) {
			curListener.notifyStatusChanged(this, newStatus);
		}
		
	}

}
