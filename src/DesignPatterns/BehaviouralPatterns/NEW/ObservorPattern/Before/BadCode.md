### The Hard-Coded Notification Problem

In a naive implementation, the **Subject** (the YouTube Channel) takes on the burden of managing its **Observers** (Subscribers) manually. This results in a rigid, fragile system where the core business logic is buried under a pile of notification details.

---

## 1. Understanding the Issue
Imagine we’re building a simple **YouTube-like Notification System**. Whenever a creator uploads a new video, all their subscribers should get notified. In the current design, the `YoutubeChannel` class is forced to know exactly who is subscribed and exactly how they want to be reached.



---

## 2. What’s Wrong with This Approach?

### Tightly Coupled Code
* **The Issue**: The `YoutubeChannel` class is directly responsible for how users are notified. 
* **The Impact**: If tomorrow we want to send an SMS or push notification, we have to edit the core `uploadNewVideo` method. The "What" (uploading) is stuck to the "How" (notifying).

### Violation of Single Responsibility Principle (SRP)
* **The Issue**: The class is doing two things: handling video uploads and managing user communication protocols. 
* **The Impact**: Ideally, the channel should only care about the video content. By managing email strings and notification types, the class becomes bloated and difficult to test.

### Violation of Open/Closed Principle (OCP)
* **The Issue**: The class is "Open for modification." Every time a new user clicks the "Subscribe" button, a developer would technically need to add a new line of code to the `YoutubeChannel` class.
* **The Impact**: Software should be open for extension but **closed for modification**. This design forces you to break that rule constantly.

### No Runtime Flexibility (Scalability Issues)
* **The Issue**: Subscribers are hard-coded into the method. 
* **The Impact**: There is no way for a user to "Unsubscribe" or change their notification preferences at runtime. The system cannot scale to handle hundreds or thousands of users dynamically.

---

## 3. Structural Analysis

| Feature | Status | Observation |
| :--- | :--- | :--- |
| **Dependency** | **Rigid** | The Channel depends on concrete email addresses. |
| **Logic** | **Hard-coded** | Notifications are baked into the upload method. |
| **Maintenance** | **High** | Every subscriber change requires a code deployment. |
| **Dynamic Behavior** | **Impossible** | Users cannot join or leave while the app is running. |

---

## 4. Summary of the "Smell"
When you see a method that performs a core action and then proceeds to manually call several unrelated services (Email, SMS, Logging), you are looking at a **missing subscription mechanism**. 

The **Observer Pattern** fixes this by allowing the Channel to say: *"I just uploaded a video; I don't care who is listening, but here is the update."* The observers then handle the "how" independently.

---
