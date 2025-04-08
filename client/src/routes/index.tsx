import { createFileRoute } from '@tanstack/react-router'
import SearchBar from './- components/SearchBar'
import {
  QueryClient,
  QueryClientProvider,

} from '@tanstack/react-query'

const queryClient = new QueryClient()

export const Route = createFileRoute('/')({
  component: Index,
})

function Index() {
  return (
    <div style={{padding: "2px"}}>
      <h3>Welcome Home!</h3>
      <QueryClientProvider client={queryClient}>
        <SearchBar />
      </QueryClientProvider>
      
    </div>
  )
}