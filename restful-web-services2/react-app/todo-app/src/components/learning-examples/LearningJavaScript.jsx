const person = { // dictionary
    name: 'Ranga',
    address: {
        line1: '123 Baker Street London',
        city: 'London',
        country: 'UK',
    },
    profiles: ['twitter', 'linkedin', 'instagram'],//array
    printProfile: () => { // define function, lambda와 형식이 비슷함, 진짜 lambda인가?
        person.profiles.map( // map()
            profile => console.log(profile)
        )
    }
    
}
export default function LearningJavaScript(){
    return(
        <>
            <div>{person.name}</div>
            <div>{person.address.line1}</div>
            <div>{person.address.city}</div>
            <div>{person.address.country}</div>
            <div>{person.profiles[0]}</div>
            <div>{person.printProfile()}</div>
        </>
    )
}