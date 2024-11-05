
export interface Profile{
    userName: string,
    userType: string,
    tastes: string,
    topicOfInterest: string,
    hobbies: string,
    contentType: string,
    description: string,
    photo: File
    subscriberList: Profile[]
}