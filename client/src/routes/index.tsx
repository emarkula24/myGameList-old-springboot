import { createFileRoute } from '@tanstack/react-router'
import Search from './- components/search'
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
        <Search />
      </QueryClientProvider>
      
    </div>
  )
}