import { useContext } from "react"
import { AuthContext } from "./security/AuthContext"

function FooterComponent(){
    const authContext = useContext(AuthContext)
    
    console.log(`Footer Component - ${authContext.number}`)

    return(
        <footer className="footer">
            <div className="container">
                Your Footer 
            </div>
        </footer> 
    )
}

export default FooterComponent