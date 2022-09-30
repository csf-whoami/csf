/**
 * 
 */
package com.csf.security.template;

/**
 * @author mba0051
 *
 */
//@Component("cityService")
public class Template_Flux_Mono {

//	@Autowired
//	private CityRepository cityRepository;
//
//	@Autowired
//	private TransactionTemplate transactionTemplate;
//
//	@Autowired
//	@Qualifier("jdbcScheduler")
//	private Scheduler jdbcScheduler;
//
//	@Override
//	public Mono<City> getCity(String name, String country) {
//		Mono<City> city = Mono
//				.defer(() -> Mono.just(this.cityRepository.findByNameAndCountryAllIgnoringCase(name, country)))
//				.subscribeOn(jdbcScheduler);
//
//		// Alternative to Mono.defer(). we can also use Mono.fromCallable().
//		// Reference: https://spring.io/blog/2016/07/20/notes-on-reactive-programming-part-iii-a-simple-http-server-application
////		Mono<City> city = Mono
////				.fromCallable(() -> this.cityRepository.findByNameAndCountryAllIgnoringCase(name, country))
////				.subscribeOn(jdbcScheduler);
//
//		return city;
//	}
//
//	@Override
//	public Flux<City> findAll() {
//		Flux<City> defer = Flux.defer(() -> Flux.fromIterable(this.cityRepository.findAll()));
//		return defer.subscribeOn(jdbcScheduler);
//	}
//
//	@Override
//	public Mono<City> add(String name, String country) {
//		return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
//			City city = new City(name, country);
//			City savedCity = cityRepository.save(city);
//			return savedCity;
//		})).subscribeOn(jdbcScheduler);
//	}
}

//@Slf4j
//@Service
//public class MemoService {
//	private final MemoRepository memoRepository;
//
//	public MemoService(MemoRepository memoRepository) {
//		this.memoRepository = memoRepository;
//	}
//
//	public Mono<Memo> save(MemoRequestDTO memoRequestDTO) {
//		log.info("===== MemoService 시작 =====");
//		return Mono.fromCallable(() -> memoRepository.save(Memo.of(memoRequestDTO))).subscribeOn(Schedulers.elastic()).log();
//	}
//}