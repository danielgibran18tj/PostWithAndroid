package com.example.ensayopruebabg2.domain.executor;

import io.reactivex.Scheduler;

/**
 * Thread abstraction created to change the execution context from
 * UI thread(main) to job thread(data).
 */
public interface JobScheduler {
    Scheduler getScheduler();
}
