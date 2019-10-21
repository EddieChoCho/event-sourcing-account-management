# A project for practicing Event-Sourcing

## Core Concepts of Event-Sourcing

* Event: An event is something that has happened in the past. Conceptually, it should be Immutable.
* EventStream: A sequence of events.
* EventStore: An event store is a type of database optimized for storage of events.
* Projection: deriving current state of specific perception from the event stream.

## API Document
- [Bank Account Management API](https://github.com/EddieChoCho/event-sourcing-account-management/blob/master/Endpoints.md)

## References

- [Greg Young - CQRS and Event Sourcing - Code on the Beach 2014](https://youtu.be/JHGkaShoyNs)
- [GOTO 2017 • The Many Meanings of Event-Driven Architecture • Martin Fowler](https://youtu.be/STKCRSUsyP0)
- [Event Sourcing You are doing it wrong by David Schmitz](https://youtu.be/GzrZworHpIk)
- [Building an Event Storage - CQRS ](https://cqrs.wordpress.com/documents/building-event-storage/)
