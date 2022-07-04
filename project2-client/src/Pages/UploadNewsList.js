import React , {useEffect, useState} from 'react'
import axios from 'axios';

function UploadNewsList() {

    const [selectedFile, setSelectedFile] = useState()
    const [pageID, setPageID] = useState(1)
    const [pageList, setPageList] = useState([])

    const onFileChange = event => {
       setSelectedFile(event.target.files[0]);
    };

    const handleOptionChange = (e) => {
        setPageID(e.target.value);
        console.log(e.target.value);
    }
  
    const onFileUpload = () => {
        // Create an object of formData
        const formData = new FormData();
        // Update the formData object
        formData.append(
          "upload_file", selectedFile,
        );
        formData.append(
            "page_source_id", pageID
        )
        // Request made to the backend api
        // Send formData object
        axios.post("http://localhost:8082/api/admin/uploadListNews", formData)
        .then(reponse => {console.log(reponse.data)})
        .catch(err => {
            console.log(err)
        })
        ;
    }

    useEffect(() => {
        fetch("http://localhost:8082/api/admin/all_page_source")
          .then(res => res.json())
          .then(data => {
            if(!data.errors){
              setPageList(data)
            }
            else {
              console.log("failed");
            }
          })
      }, [])

    return (
        <div>
            <div>
                <input type="file" onChange={onFileChange} />
            </div>
            <select name="page_source_id" value={pageID} onChange={handleOptionChange}>
                {pageList.map((page, index) => (
                    <option value={page.id} key={index}>
                        {page.name}
                    </option>
                ))}
            </select>
            <button onClick={onFileUpload}>
                Upload!
            </button>
    </div>
    );
}

export default UploadNewsList

// return (
//     <div>
//         <form
//             action = "http://localhost:8082/api/admin/uploadListNews"
//             encType=    "multipart/form-data"
//             method="POST" >
//             <input type="file" name="upload_file" />
//             <button>Submit</button>
//         </form>
//     </div>
// )